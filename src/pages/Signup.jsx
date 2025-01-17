import React,{useState} from 'react'
import '../CSS/Signup.css'
import logo from'../images/todologo.png';

const Signup = () =>{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [nickname, setNickname] = useState("");

 const handleSignup = () => {
    if(email === '' || password === '' || confirmPassword === '' || nickname === '') {
        alert('이메일, 비밀번호, 비밀번호 확인, 닉네임을 입력해주세요.');
        return;
    }
    if(password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
    console.log('Email:', email);
    console.log('Password:', password);
    console.log('Nickname:', nickname);
}
    return(
       <div className='signup-container'>
            <div className='signup-title-container'>
                <img className='signup-title-logo' src={logo} alt='logo'/>
                <h1 className='signup-title'>회원가입</h1>
            </div>

            <div className='signup-form-container'>
                <input className='signup-input' type='email' placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)}/>
                <input className='signup-input' type='password' placeholder='Password' value={password} onChange={(e) => setPassword(e.target.value)}/>
                <input className='signup-input' type='password' placeholder='비밀번호 확인' value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}/>
                <input className='signup-input' type='text' placeholder='닉네임' value={nickname} onChange={(e) => setNickname(e.target.value)}/>
                <button className='signup-button'onClick={handleSignup}>회원가입</button>
            </div>
       </div>
    );
}
export default Signup;