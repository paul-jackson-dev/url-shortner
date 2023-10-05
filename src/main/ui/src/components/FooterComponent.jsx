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
                    <span className = "text-muted">
                        <a href="https://github.com/paul-jackson-dev" className='text-muted' idName="footerlink">$$$</a></span>
                </footer>   
            </div>
        );
    }
}

export default FooterComponent;