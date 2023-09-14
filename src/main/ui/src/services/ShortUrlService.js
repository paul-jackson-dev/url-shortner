import api from "../api/BaseUrlConfig.js";

// const SHORTURL_API_BASE_URL = "http://localhost:8080/api/";

class ShortUrlService{


    getShortUrl(){
        return api.get();
    }

}

export default new ShortUrlService();  //export service object