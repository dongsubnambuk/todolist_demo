import React, { useState } from 'react';
import '../CSS/todo.css';

const Todo = () => {
    // useState는 React에서 상태를 관리하는 데 사용됨.
    // 입력값을 관리하는 state
    const [inputValue, setInputValue] = useState('');
    // todo 리스트를 관리하는 state
    const [todos, setTodos] = useState([]);

    // 입력값 변경
    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    // todo 추가
    const handleAddTodo = () => {
        
        // tirm() 메서드를 사용하여 입력값의 앞뒤 공백을 제거
        if (inputValue.trim() !== '') {
            // 입력값이 비어있지 않으면 새로운 todo 추가
            setTodos([
                ...todos, // ...(스프레드 연산자) : 기존 배열을 복사, 새로운 요소 추가, 새로운 배열 반환
                {
                    id: Date.now(), // 고유한 id 생성, 오늘 날짜 사용
                    text: inputValue, // 입력값 설정
                    completed: false // 초기에는 완료되지 않은 상태로 설정
                }
            ]);
            setInputValue(''); // 입력창 초기화
        }
        
        // 입력값이 비어있으면 알림 메시지 표시
        if (inputValue.trim() === '') {
            alert('할 일을 입력해주세요.');
        }
    };

    // Enter 키 입력
    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleAddTodo();
        }
    };

    // todo 삭제
    const handleDeleteTodo = (id) => {
        // 해당 id를 가진 todo를 제외한 새로운 배열 생성
        // 즉, 해당 id를 가진 todo를 제외한 나머지 todo만 포함하는 새로운 배열 생성
        setTodos(todos.filter(todo => todo.id !== id));
        // filter() 메서드를 사용하여 해당 id를 가진 todo를 제외한 나머지 todo만 포함하는 새로운 배열 생성
        // filter() 메서드는 콜백 함수를 인자로 받으며, 콜백 함수는 배열의 각 요소에 대해 실행됩니다.
        // 콜백 함수는 배열의 각 요소를 순회하면서 조건을 확인하고, 조건을 만족하는 요소만 새로운 배열에 포함시킵니다.
        // todos 배열에서 todo.id가 id와 같지 않은 요소만 포함하는 새로운 배열을 생성하고, 이를 setTodos 함수를 사용하여 상태를 업데이트합니다. => todo.id가 id인 것을 제거
    };

    // todo 완료 미완료 개수 표시
    const unDoneTodoList = todos.filter((todo) => !todo.completed);
    const doneTodoList = todos.filter((todo) => todo.completed);

    // todo 완료
    const handleToggleComplete = (id) => {
        setTodos(todos.map(todo => 
            todo.id === id ? { ...todo, completed : !todo.completed } : todo
          // 조건 연산자(삼항 연산자)를 사용하여 todo.id가 id와 같은 경우에는 todo.completed 값을 반전시키고, 그렇지 않은 경우에는 원래의 todo 객체를 그대로 유지합니다.
          // 조건 연산자(삼항 연산자)는 조건 ? 참일 때 값 : 거짓일 때 값 형태로 사용.
          // completed : !todo.completed은 todo.completed 값을 반전시킴(true -> false, false -> true)
          // : todo는 원래의 todo 객체를 그대로 유지
        ));
        // 함수 전체 흐름
            // todos 배열을 순회하면서 todo.id가 id와 같은 요소를 찾습니다.
            // 해당 요소가 존재하면 todo.completed 값을 반전시키고, 그렇지 않으면 원래의 todo 객체를 그대로 유지합니다.
            // 이렇게 수정된 새로운 배열을 setTodos 함수를 사용하여 상태를 업데이트합니다.
            // 이를 통해 todo의 completed 값을 반전시키고, 상태를 업데이트합니다.

        // map()
        // 반복되는 컴포넌트를 렌더링하기 위해 사용
        // 원본배열.map((요소, 인덱스, 배열) => { return 새로운 요소 })
    };

    return (
        <>
            <div className='todo-container-title'>
                <h1 className='todo-container-title-inner'>Todo List</h1>
            </div>

            <div className='todo-container-input'>
                <input 
                    type='text' 
                    placeholder='할일을 입력하세요' 
                    className='todo-container-input-inner'
                    value={inputValue}
                    onChange={handleInputChange}
                    onKeyPress={handleKeyPress}
                />
                <button 
                    className='todo-container-input-btn'
                    onClick={handleAddTodo}
                >
                    등록
                </button>
            </div>

            {/* 완료 미완료 개수 표시 */}

            <div className='todo-container-count'>
                <span>☑️ {unDoneTodoList.length}개 남음 </span>
                <span>✅ {doneTodoList.length}개 완료</span>
            </div>

            <div className='todo-container-list'>
                <ul className='todo-container-list-inner'>
                    {todos.map(todo => (
                        // 각 todo 항목을 렌더링하는 부분
                        // key 속성은 React에서 각 요소를 구분하는 데 사용되는 고유한 식별자를 제공합니다.
                        // 이를 통해 각 요소를 추적하고 업데이트할 수 있습니다.
                        // key 속성은 각 요소에 대해 유일한 값을 제공해야 함.
                        <li key={todo.id}>
                            <input 
                                type='checkbox'
                                checked={todo.completed}
                                onChange={() => handleToggleComplete(todo.id)}
                            />
                            <span style={{ 
                                textDecoration: todo.completed ? 'line-through' : 'none'
                            }}>
                                {todo.text}
                            </span>
                            <button onClick={() => handleDeleteTodo(todo.id)}>
                                삭제
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
         
        </>
    );
};

export default Todo;