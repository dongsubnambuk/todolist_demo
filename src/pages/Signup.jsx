import React,{useState} from 'react'
import '../CSS/Signup.css'
import logo from'../images/todologo.png';
import { useNavigate } from 'react-router-dom';

const Signup = () =>{
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [nickname, setNickname] = useState("");
    const navigate = useNavigate();

  //회원가입
  const handleSignup = async (event) => {
    event.preventDefault();

    const response = await fetch("https://refresh-f5-server.o-r.kr/todo/auth/sign-up", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        password: password,
        name: nickname,
        email: email,
     
      }),
    });

    const result = await response.json();

    if (response.status === 200) {
      console.log(result);
      alert("회원가입 성공하였습니다. 로그인 해주세요.");
      navigate("/login");
    } else {
      console.log("회원가입 실패");
      alert("회원가입 실패: " + result.message);
    }
  };
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