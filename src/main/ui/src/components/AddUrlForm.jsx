import api from "../api/BaseUrlConfig.js";
import { useState } from "react";

function AddUrlForm(){

    const [text, setText] = useState('')

    const handleSubmit = async (e) => {
        e.preventDefault()
        console.log("consoleingasdfd" + text);
        const result = await api.post('add', text)
        console.log(result.data)
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    Shorten a Url
                </label>
                <input type="text" id="text" value={text} onChange={e => setText(e.target.value)}></input>
                <button type="submit">Make It Short</button>
            </form>
        </div>
    )

}

export {AddUrlForm};