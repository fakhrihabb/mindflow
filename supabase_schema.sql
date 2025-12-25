-- Enable UUID extension
create extension if not exists "uuid-ossp";

-- Table: sessions
create table public.sessions (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references auth.users(id) not null,
  created_at timestamp with time zone default timezone('utc'::text, now()) not null,
  ended_at timestamp with time zone,
  is_completed boolean default false,
  core_thought text,
  emotion_initial integer,
  emotion_final integer,
  cognitive_distortions text[], -- Array of strings
  summary text
);

-- Table: messages
create table public.messages (
  id uuid primary key default uuid_generate_v4(),
  session_id uuid references public.sessions(id) on delete cascade not null,
  user_id uuid references auth.users(id) not null,
  role text not null check (role in ('USER', 'AI', 'SYSTEM')),
  content text not null,
  created_at timestamp with time zone default timezone('utc'::text, now()) not null,
  metadata jsonb -- For any extra AI data
);

-- Table: tracked_thoughts
create table public.tracked_thoughts (
  id uuid primary key default uuid_generate_v4(),
  user_id uuid references auth.users(id) not null,
  origin_session_id uuid references public.sessions(id),
  thought_text text not null,
  initial_intensity integer not null,
  current_intensity integer not null,
  status text default 'ACTIVE' check (status in ('ACTIVE', 'RESOLVED', 'ARCHIVED')),
  created_at timestamp with time zone default timezone('utc'::text, now()) not null,
  updated_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- Table: check_ins
create table public.check_ins (
  id uuid primary key default uuid_generate_v4(),
  thought_id uuid references public.tracked_thoughts(id) on delete cascade not null,
  intensity integer not null,
  note text,
  created_at timestamp with time zone default timezone('utc'::text, now()) not null
);

-- Security: Row Level Security (RLS) policies
-- This ensures users can only see their own data

-- Enable RLS
alter table public.sessions enable row level security;
alter table public.messages enable row level security;
alter table public.tracked_thoughts enable row level security;
alter table public.check_ins enable row level security;

-- Policies for Sessions
create policy "Users can view their own sessions" on public.sessions
  for select using (auth.uid() = user_id);

create policy "Users can insert their own sessions" on public.sessions
  for insert with check (auth.uid() = user_id);

create policy "Users can update their own sessions" on public.sessions
  for update using (auth.uid() = user_id);

-- Policies for Messages
create policy "Users can view messages from their sessions" on public.messages
  for select using (auth.uid() = user_id);

create policy "Users can insert messages" on public.messages
  for insert with check (auth.uid() = user_id);

-- Policies for Tracked Thoughts
create policy "Users can view their own thoughts" on public.tracked_thoughts
  for select using (auth.uid() = user_id);

create policy "Users can insert their own thoughts" on public.tracked_thoughts
  for insert with check (auth.uid() = user_id);

create policy "Users can update their own thoughts" on public.tracked_thoughts
  for update using (auth.uid() = user_id);

-- Policies for Check-ins
create policy "Users can view their own check-ins" on public.check_ins
  for select using (
    exists (
      select 1 from public.tracked_thoughts
      where tracked_thoughts.id = check_ins.thought_id
      and tracked_thoughts.user_id = auth.uid()
    )
  );

create policy "Users can insert check-ins for their thoughts" on public.check_ins
  for insert with check (
    exists (
      select 1 from public.tracked_thoughts
      where tracked_thoughts.id = check_ins.thought_id
      and tracked_thoughts.user_id = auth.uid()
    )
  );
