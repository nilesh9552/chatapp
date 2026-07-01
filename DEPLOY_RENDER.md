Deployment to Render

Steps to deploy this Spring Boot chat app to Render:

1. Create a new Web Service on Render and connect your repository (GitHub/GitLab).

2. Build & Start settings:
   - Build Command: `mvn -DskipTests package`
   - Start Command: `java -Dserver.port=$PORT -jar target/chat-app-0.0.1-SNAPSHOT.jar`

3. Environment variables:
   - For production Postgres, set `SPRING_DATASOURCE_URL` to your JDBC URL
     (e.g. `jdbc:postgresql://<HOST>:<PORT>/<DB>`), and set
     `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD`.
   - Alternatively, add Render's Postgres Add-on and set the above vars
     from the add-on's connection details.

4. Optional: Enable the H2 console for debugging (not recommended in production).
   - URL: `/h2-console` (enabled by default for local/dev via `application.properties`).

5. Use `render.yaml` (committed) to preconfigure the service if you prefer to
   deploy via Render's `render.yaml` manifest.

6. After deployment, open your service URL. The chat UI is at `/chat`.

Notes:
- This project builds an executable jar under `target/` that Render will run.
- Ensure any secrets (DB passwords) are set in Render's dashboard, not in source.
