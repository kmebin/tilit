import { Container, Row } from 'react-bootstrap';
import SignupForm from '../components/user/SignupForm';
import { signup } from '../apis/auth';

const Signup = () => {
  const handleSignup = async (form) => {
    const res = await signup(form);
    if (res.status === 201) {
      alert('회원가입 성공');
      window.location.href = '/';
    } else {
      alert('회원가입 실패');
    }
  };

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-3'>
        <h1 className='text-center'>
          <span className='text-primary'>tilit</span>
        </h1>
      </Row>
      <SignupForm onClickSignup={handleSignup} />
    </Container>
  );
};

export default Signup;
