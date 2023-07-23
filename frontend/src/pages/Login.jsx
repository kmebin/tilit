import { Container, Row } from 'react-bootstrap';
import LoginForm from '../components/user/LoginForm';
import { Link, useNavigate } from 'react-router-dom';
import { login } from '../apis/auth';

const Login = ({ setIsLogin }) => {
  const navigate = useNavigate();

  const handleLogin = async (form) => {
    const res = await login(form);
    if (res.status === 200) {
      setIsLogin(true);
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
      <LoginForm onClickLogin={handleLogin} />
    </Container>
  );
};

export default Login;
