import { Container, Row } from 'react-bootstrap';
import SignupForm from '../components/user/SignupForm';
import { signup } from '../apis/auth';
import { Link, useNavigate } from 'react-router-dom';

const Signup = () => {
  const navigate = useNavigate();

  const handleSignup = async (form) => {
    const res = await signup(form);
    if (res.status === 201) {
      alert(res.message);
      navigate('/');
    } else {
      alert(res.message);
    }
  };

  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-3'>
        <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
          <h1 className='text-center'>
            <span className='text-primary'>tilit</span>
          </h1>
        </Link>
      </Row>
      <SignupForm onClickSignup={handleSignup} />
    </Container>
  );
};

export default Signup;
