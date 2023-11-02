import api from '../api/BaseUrlConfig.js'
import { useRef, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { faCheck, faTimes }from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import '../css/Register.css'; // Import regular stylesheet


// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/; // example: Mar_k6
const USER_REGEX = /^[A-z0-9-_]{3,23}$/; // example: mark_6
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,50}$/;
const EMAIL_REGEX = /^[^\s@]+@[^\s@]+\.[^\s@]+$/  // matches string@string.string and prevents multiple @


function Register(){
    const userRef = useRef(); // sets focus on user input when function loads

    // username
    const [user, setUser] = useState("");
    const [validUser, setValidUser] = useState(false);
    const [userFocus, setUserFocus] = useState(false);

    // email
    const [email, setEmail] = useState("");
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    // password
    const [password, setPassword] = useState("");
    const [validPassword, setValidPassword] = useState(false);
    const [passwordFocus, setPasswordFocus] = useState(false);

    // matching password
    const [matchPassword, setMatchingPassword] = useState("");
    const [validMatchPassword, setValidMatchPassword] = useState(false);
    const [matchPasswordFocus, setMatchPasswordFocus] = useState(false);

    //error, success, loading
    const [errorMessage, setErrorMessage] = useState("");
    const [success, setSuccess] = useState(false);
    const [loading, setLoading] = useState(false)

    const registerDTO = {
        username: user,
        email: email,
        password: password
    };

    useEffect(() => {
        userRef.current.focus(); // sets focus when component loads on username input
    }, []) // zero dependency array

    useEffect(() => { // validate user
        const result = USER_REGEX.test(user); 
        setValidUser(result); // set valid state to true or false
    }, [user])

    useEffect(() => { // validate email
        const result = EMAIL_REGEX.test(email);
        setValidEmail(result)
    }, [email])

    useEffect(() => { // validate passwords
        const result = PWD_REGEX.test(password);
        setValidPassword(result); 
        const matching = password === matchPassword;
        setValidMatchPassword(matching);
    }, [password, matchPassword])

    useEffect(() => {
        setErrorMessage('');
    }, [user, password, matchPassword]) // after error message is read, and one of these three changes, error message will clear

    const handleSubmit = async (event) => {
        event.preventDefault(); 

        // possibly prevent a javascript hack from enabling the submit button and submitting bad data
        // if (!USER_REGEX.test(user) || !PWD_REGEX.test(password) || !EMAIL_REGEX.test(email)){
        //     setErrorMessage("Invalid Entry");
        //     return;
        // }
        console.log(registerDTO)
        setLoading(true)
        //send api post request and catch error if exists
        try {
            const result = await api.post('register', registerDTO)
            console.log(result.data)
            console.log(JSON.stringify(result)) // shows full response as string
            setSuccess(true);
            // clear state
            // setUser("");
            // setEmail("");
            // setPassword("");
            // setMatchingPassword("");
        } catch (error){
            if (!error?.response){ //optional chain, if no error response
                setErrorMessage(["Server did not respond."])
            } else if (error.response?.status === 409){
                setErrorMessage(["Username taken."])
            } else {
                let defaultError = error.response.data[0].defaultMessage;
                console.log(error.response.data)
                setErrorMessage(defaultError)
            }
        }
        setLoading(false)
    }
    
    return(
        // create ternary fragment
        <> 
        {success ? 
        (
            <section>
                <h1>Sign Up Successful!</h1>
                <p>
                    <Link to="/-/login">Log In Here</Link>
                </p>

            </section>

        ) : (
            <section>
                <p className={errorMessage ? "validError" : "hide"}>{errorMessage}</p>
                <h1>Registration</h1>
                <span className={"span-center"}>
                    {loading && "Loading..."}
                </span>
                <form onSubmit = {handleSubmit}>
                    {/* //htmlFor sets focus on username input when label is clicked */}
                    <label htmlFor="username">
                        Username:
                        <span className={validUser ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faCheck} size="lg" style={{color: "#212529",}} />
                        </span>
                        <span className={!validUser && userFocus ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faTimes} size="lg" style={{color: "#212529",}} />
                        </span>
                    </label>        
                    <input
                        type = "text"
                        id = "username"
                        ref = {userRef}  
                        onChange = {(e) => setUser(e.target.value)}     
                        required
                        onFocus = {() => setUserFocus(true)}
                        // onBlur is for when user leaves input box
                        onBlur = {() => setUserFocus(false)}
                    />
                    <p className = {userFocus && user && !validUser ? "instructions" : "hide"}>
                        3 - 50 characters <br />
                        Must begin with a letter. <br />
                        Letters, numbers, underscores, hyphens allowed.
                    </p>

                    <label htmlFor="email">
                        Email:
                        <span className={validEmail ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faCheck} size="lg" style={{color: "#212529",}} />
                        </span>
                        <span className={!validEmail && emailFocus ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faTimes} size="lg" style={{color: "#212529",}} />
                        </span>
                    </label>        
                    <input
                        type = "email"
                        id = "email" 
                        onChange = {(e) => setEmail(e.target.value)}     
                        required
                        onFocus = {() => setEmailFocus(true)}
                        onBlur = {() => setEmailFocus(false)}
                    />
                    <p className = {emailFocus && email && !validEmail ? "instructions" : "hide"}>
                        Sample format: example@example.com <br />
                    </p>

                    <label htmlFor="password">
                        Password:
                        <span className={validPassword ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faCheck} size="lg" style={{color: "#212529",}} />
                        </span>
                        <span className={!validPassword && password ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faTimes} size="lg" style={{color: "#212529",}} />
                        </span>
                    </label>        
                    <input
                        type = "password"
                        id = "password"
                        onChange = {(e) => setPassword(e.target.value)}     
                        required
                        onFocus = {() => setPasswordFocus(true)}
                        onBlur = {() => setPasswordFocus(false)}
                    />
                    <p className = {passwordFocus && !validPassword ? "instructions" : "hide"}>
                        8 - 50 characters <br />
                        Must contain one uppercase and lowercase letter, a number and a special character <br />
                        Special characters allowed: !@#$%
                    </p>

                    <label htmlFor="verifyPassword">
                        Verify Password:
                        <span className={validMatchPassword && validPassword ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faCheck} size="lg" style={{color: "#212529",}} />
                        </span>
                        <span className={!validMatchPassword && matchPassword ? "valid" : "hide"}>
                        <FontAwesomeIcon icon={faTimes} size="lg" style={{color: "#212529",}} />
                        </span>
                    </label>        
                    <input
                        type = "password"
                        id = "verifyPassword"
                        onChange = {(e) => setMatchingPassword(e.target.value)}     
                        required
                        onFocus = {() => setMatchPasswordFocus(true)}
                        onBlur = {() => setMatchPasswordFocus(false)}
                    />
                    <p className = {matchPasswordFocus && !validMatchPassword ? "instructions" : "hide"}>
                        Passwords do not match.
                    </p>
                    <button disabled = {!validUser || !validPassword || !validMatchPassword ? true : false } className={!validUser || !validPassword || !validMatchPassword ? "disabled" : "none"}>
                        Sign up
                    </button>
                    <p>Already registered? <br />
                    <Link to="/-/login">Log in</Link>
                    </p>
                </form>
                
            </section>
            )}
        </>
    )

}



export {Register};  