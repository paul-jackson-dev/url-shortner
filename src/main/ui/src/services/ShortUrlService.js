import axios from 'axios';

const SHORTURL_API_BASE_URL = "http://localhost:8080/api/";

class ShortUrlService{


    getShortUrl(){
        return axios.get(SHORTURL_API_BASE_URL);
    }

}

export default new ShortUrlService();  //export service object