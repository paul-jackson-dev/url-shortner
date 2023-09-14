import React, { Component } from 'react'; // created with rcc command React Class Component

class FooterComponent extends Component {
    constructor(props){
        super(props)
        this.state = {}
    }
    
    render() {
        return (
            <div>
                <footer className="footer">
                    <span className = "text-muted">$$$</span>
                </footer>   
            </div>
        );
    }
}

export default FooterComponent;