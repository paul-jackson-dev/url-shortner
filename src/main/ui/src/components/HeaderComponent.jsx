import React, { Component } from 'react'; // created with rcc command React Class Component

class HeaderComponent extends Component {
    constructor(props){
        super(props)
        this.state = {}
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="nav justify-content-center navbar navbar-expand-md navbar-dark bg-dark">
                        <div>
                            <a href="http://localhost:8080/" className="navbar-brand">&nbsp;&nbsp; Make it Short Please</a>
                        </div>
                    </nav>
                </header>
                
            </div>
        );
    }
}

export default HeaderComponent;  