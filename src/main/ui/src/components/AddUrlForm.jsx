import api from "../api/BaseUrlConfig.js";
import { useState } from "react";

function AddUrlForm(){

    const [longUrl, setLongUrl] = useState('')
    const [loading, setLoading] = useState(false)
    const [urlInformation, setUrlInformation] = useState([])

    const addUrlFormDTO = {
        longUrl: longUrl,
        user: null
    };

    const handleSubmit = async (e) => {
        e.preventDefault()
        setLoading(true)
        const result = await api.post('add', addUrlFormDTO)
        setLoading(false)
        setUrlInformation(result.data)
        console.log(result.data)
    }

    return (
        <section id="urlbox">
            <h1>Paste the URL to be shortened</h1>
            <div className="form" idName="formurl">
                <form onSubmit={handleSubmit}>
                    <input type="text" id="text" placeholder="Shorten a Url" value={longUrl} onChange={e => setLongUrl(e.target.value)}></input>
                    <button type="submit" className="btn btn-dark">Make It Short Please</button>
                </form>
                <br></br>
                <p className="boxtextcenter">
                Make It Short is a free tool to generate short links and track clicks.
                </p>
                <div>
                    {loading && "Loading..."}
                </div>
                {urlInformation.length === 0 ? "" 
                :
                <div key={urlInformation.id}>
                    <p>Short Url: {urlInformation.topLevelDomain + urlInformation.shortUrl}</p>
                </div>}
            </div>
        </section>
    )

}

export {AddUrlForm};