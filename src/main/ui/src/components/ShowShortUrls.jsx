import api from "../api/BaseUrlConfig.js";
import { useEffect, useState } from "react";

function ShowShortUrls(){

    const [shortUrls, setShortUrls] = useState([])
    const [error, setError] = useState(false)
    const [loading, setLoading] = useState(false);


    // loads first thing when component loads
    // useEffect can't be async, but we can load an async function inside of it.
    // populates an empty array and only makes one request
    useEffect(() => {
         ;(async () =>{
        setError(false); //always set error to false on reload
        setLoading(true) // show that data is loading
        try {
            const result = await api.get('shorturls')
            setShortUrls(result.data); // use state to set returned data
         } catch (error){
            setError(true);
         }
         setLoading(false)

         })()
    }, []) //dependency array


    return( 
        <div>
            <h1>Short Urls</h1>
            {loading && <p>Loading data...</p>}
            {error ?
            <p>Couldn't connect to the API</p>
            :

            shortUrls.map( (shortUrl) => (
                <div key={shortUrl.id}>
                    {console.log(shortUrl.longUrl)}
                    <h5><a href={shortUrl.longUrl}>{"http://localhost:8080/"+ shortUrl.shortUrl}</a></h5>
                </div>
            ))}
        </div>
        );

}

export {ShowShortUrls};  