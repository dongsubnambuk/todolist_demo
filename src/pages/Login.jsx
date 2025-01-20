import React, { useState } from 'react'
import '../CSS/Login.css'
import logo from'../images/todologo.png';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
        const navigate = useNavigate();


    //login fetch
    const handleUserLogin = async (event) => {
        event.preventDefault();
    
        try {
          const response = await fetch('https://refresh-f5-server.o-r.kr/todo/auth/sign-in', {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              email: email,
              password: password,
            }),
          });
    
          const result = await response.json();
    
          if (response.status === 200) {
            console.log(result);
            localStorage.setItem("email", result.user.email);
            localStorage.setItem("id", result.user.id);
            console.log("로그인 성공");
            navigate('/');
          } else {
            console.log("로그인 실패");
            
              alert("로그인 실패: " + result.message);
          }
        } catch (error) {
          console.error("Fetch error: ", error);
        }
      };
    return(
        <div className='login-container'>
            <div className='login-title-container'>
                <img className='login-title-logo' src={logo} alt='logo'/>
            </div>

            <div className='login-title-container'>
                <h1 className='login-title'>로그인</h1>
            </div>

            <div className='login-form-container'>
                <input className='login-input' type='email' placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)}/>
                <input className='login-input' type='password' placeholder='Password' value={password} onChange={(e) => setPassword(e.target.value)}/>
                <button className='login-button' onClick={handleUserLogin}>Login</button>
            </div>

            <div className='login-signup-container'>
                <span>
                    아직 회원이 아니신가요?
                </span>
                <a href='/signup'>회원가입</a>
            </div>
        </div>
    );
}
export default Login;