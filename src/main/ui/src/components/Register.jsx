import { useRef, useState, useEffect} from "react";

// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/; // example: Mar_k6
const USER_REGEX = /^[A-z0-9-_]{3,23}$/; // example: mark_6
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

function Register(){
    const useRef = useRef(); // sets focus on user input when function loads

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

    //error and success
    const [errorMessage, setErrorMessage] = useState("");
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        useRef.current.focus();
    }, []) // zero dependency array

    useEffect(() => {
        const result = PWD_REGEX.test(user);
    }, [user])
    
    return(
        <div>

        </div>
    )

}



export {Register};  