
import com.squareup.okhttp.*;

public class DiscordEmbedSender {
    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/WEBHOOK_ID/WEBHOOK_TOKEN";

    public static void sendEmbed(String title, String description, String color, boolean madeByOzaii, boolean includeImage, String imageUrl) {
        OkHttpClient httpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String madeByText = madeByOzaii ? "Made by Ozaii" : "";

        String json = "{"
                + "\"embeds\": [{"
                + "\"title\": \"" + title + "\","
                + "\"description\": \"" + description + "\","
                + "\"color\": " + color;

        if (includeImage) {
            json += ", \"image\": {"
                    + "\"url\": \"" + imageUrl + "\""
                    + "}";
        }

        json += ", \"footer\": {"
                + "\"text\": \"" + madeByText + "\""
                + "}"
                + "}]"
                + "}";

        RequestBody requestBody = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(WEBHOOK_URL)
                .post(requestBody)
                .build();

        try {
            httpClient.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

