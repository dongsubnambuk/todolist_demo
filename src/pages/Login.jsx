import React, { useState } from 'react'
import '../CSS/Login.css'
import logo from'../images/todologo.png';

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = () => {
        if(email === '' || password === '') {
            alert('이메일과 비밀번호를 입력해주세요.');
            return;
        }
        console.log('Email:', email);
        console.log('Password:', password);
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
                <button className='login-button' onClick={handleLogin}>Login</button>
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