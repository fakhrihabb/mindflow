# Supabase Setup Guide for MindFlow

Follow these steps to configure your cloud backend.

## 1. Create Project
1.  Go to [database.new](https://database.new) (Supabase).
2.  Click **"New Project"**.
3.  Name it `MindFlow`.
4.  Set a strong database password (save this, but we won't strictly need it for the app, just for the DB).
5.  Select a region close to your target users (e.g., Singapore/Tokyo for SE Asia).
6.  Click **"Create new project"**.

## 2. Database Schema
1.  Wait for the project to finish provisioning.
2.  In the left sidebar, click the **SQL Editor** icon (looks like a terminal `>_`).
3.  Click **"New Query"**.
4.  Open the file `supabase_schema.sql` located in your project root folder (`c:/Coding_Projects/mindflow/supabase_schema.sql`).
5.  Copy all the content from that file.
6.  Paste it into the Supabase SQL Editor.
7.  Click **"Run"** (bottom right of the editor).
    *   *Success:* You should see "Success, no rows returned".

## 3. Authentication
1.  In the left sidebar, click the **Authentication** icon (looks like a Users group).
2.  Click **Providers**.
3.  Ensure **Email** is enabled (it usually is by default).
4.  (Optional) Disable "Confirm email" if you want faster testing without verifying emails.
    *   Go to **Authentication** -> **URL Configuration** -> **Site URL**.
    *   Set this to `io.supabase.mindflow://login` (for deep linking later) or just `http://localhost:3000` for now.

## 4. Get API Keys
1.  In the left sidebar, click the **Project Settings** icon (cogwheel at the bottom).
2.  Click **API**.
3.  Look for:
    *   **Project URL**: `https://xyz...supabase.co`
    *   **anon public**: `eyJ...` (Enable the "Reveal" eye icon to copy).
    
## 5. Add to Code (Securely)
1.  Open `local.properties` in your project root (create it if it doesn't exist - this file is git-ignored).
2.  Add your keys like this:

    ```properties
    sdk.dir=... (existing lines)
    
    SUPABASE_URL=https://your-project.supabase.co
    SUPABASE_KEY=your-anon-key
    ```
3.  Sync your project with Gradle (click the Elephant icon or run `./gradlew build`).
    *   This will auto-generate the `BuildKonfig` class with your keys.
    *   If you see "Unresolved reference: BuildKonfig", just run the build again.

## 6. (Optional) Storage
If we decide to upload Mindmap images later:
1.  Go to **Storage**.
2.  Create a new bucket named `mindmaps`.
3.  Make it Public.
