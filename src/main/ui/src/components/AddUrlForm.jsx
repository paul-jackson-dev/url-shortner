import api from "../api/BaseUrlConfig.js";
import { useState } from "react";
import { Link } from 'react-router-dom'



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
        setLongUrl('')
        setUrlInformation(result.data)
        console.log(result.data)
    }

    const handleFocus = (event) => event.target.select(); // select all in text box

    return (
        <section id="urlbox">
            {urlInformation.length === 0 ? 
            <div>
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
                </div>
            </div>
            :
            <div>
                <h1>Copy Your Short URL</h1>
                <div id="formurl" class="mw450" key={urlInformation.id}>
                    <input id="shortenurl" type="text" value={urlInformation.topLevelDomain + urlInformation.shortUrl} onFocus={e => handleFocus(e)}></input>
                    <div id="formbutton">
                        <input type="button" data-clipboard-target="#shortenurl" class="btn btn-dark" value="Copy URL" onClick={() =>  navigator.clipboard.writeText(urlInformation.topLevelDomain + urlInformation.shortUrl)}></input>
                    </div>
                </div>
                    {/* <div id="formurl" class="mw450dblock">
                        <div id="balloon" style={{display: 'table'}}>URL Copied
                    </div> */}
                    <br></br>
                {/* <div id="formurl" class="mw450dblock"> */}
                <div class="mw450dblock">
                    <p class="boxtextleft">
                    Long URL: <a class="text-muted" href={urlInformation.longUrl} target="_blank">{urlInformation.longUrl}</a>
                    <br></br><br></br>
                    <Link to={`-/clicks/?shortUrl=${urlInformation.shortUrl}`} className="btn btn-dark" id="linkbutton">Track Clicks</Link>
                    {/* <a href="http://localhost:3000" class="colorbuttonsmall">Make something else short</a> */}
                    <br></br><br></br>
                    <Link to={`/`} className="btn btn-dark" id="linkbutton" onClick={() =>setUrlInformation([])}>Make Something Else Short</Link>
                    </p>
                </div>
            
            </div>
            }
    </section>
    )

}

export {AddUrlForm};