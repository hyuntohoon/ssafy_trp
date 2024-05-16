export default class Convert {
    static async convertToJson(response) {
        try {
            const data = await response.json();

            return data;
        } catch (error) {
            console.error("Error:", error);
        }
    }
}
