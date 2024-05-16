let baseUrl = "http://localhost";

export default class Fetch {
    static async postRequest(url, payload) {
        try {
            const appendedUrl = baseUrl + url;
            const response = await fetch(appendedUrl, {
                method: "POST",
                body: payload,
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
            });

            return response;
        } catch (error) {
            console.error("Error:", error);
        }
    }

    static async getRequest(url) {
        try {
            const appendedUrl = baseUrl + url;
            const response = await fetch(appendedUrl);

            return response;
        } catch (error) {
            console.error("Error:", error);
        }
    }
}
