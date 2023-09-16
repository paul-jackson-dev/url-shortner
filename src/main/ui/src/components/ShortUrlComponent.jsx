// using Reactjs Code Snippets extension. rcc for react class component

import React, { Component } from 'react';
import ShortUrlService from '../services/ShortUrlService';

class ShortUrlComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            shortUrls: []
        }
        this.shortenUrl = this.shortenUrl.bind(this); // recommended way to bind paths in React using an Event Handler, possibly dated
    }

    componentDidMount(){ //called immediately after a component is mounted
        ShortUrlService.getShortUrl().then((res) =>{ //call API
            this.setState({ shortUrls : res.data});
        }); 

    }

    shortenUrl(){
        this.props.history.push('/shorten-url'); 
        // React Router mains a history object and the history object has all of the mapping of the Routes
        // The history object of each Router is passed via props and we can manually control paths by push.
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Short Url</h2>
                <div>
                    <button className="btn btn-primary" onClick={this.shortenUrl}>Shorten a Link</button>
                </div>
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


