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
        <div className="form">
            <form onSubmit={handleSubmit}>
                <input type="text" id="text" placeholder="Shorten a Url" value={longUrl} onChange={e => setLongUrl(e.target.value)}></input>
                <button type="submit" className="form button">&nbsp;Make It Short&nbsp;</button>
            </form>
            <div>
                {loading && "Loading..."}
            </div>
            {urlInformation.length === 0 ? "" 
            :
            <div key={urlInformation.id}>
                <p>Short Url: {urlInformation.shortUrl}</p>
            </div>}
        
        </div>
    )

}

export {AddUrlForm};