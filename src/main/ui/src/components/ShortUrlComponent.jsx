// using Reactjs Code Snippets extension. rcc for react class component

import React, { Component } from 'react';
import ShortUrlService from '../services/ShortUrlService';

class ShortUrlComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            shortUrls: []
        }
    }

    componentDidMount(){ //called immediately after a component is mounted
        ShortUrlService.getShortUrl().then((res) =>{ //call API
            this.setState({ shortUrls : res.data});
        });

    }

    render() {
        return (
            <div>
                <h2 className="text-center">Short Url</h2>
                <div>
                    {
                        this.state.shortUrls.map(
                            shortUrl =>
                            <p>
                                {shortUrl.shortUrl}
                            </p>
                        )
                    }
                </div> 
            </div>
        );
    }
}

export default ShortUrlComponent;


