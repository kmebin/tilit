import { Container, Row } from 'react-bootstrap';
import LoginForm from '../components/user/LoginForm';

const Login = () => {
  return (
    <Container fluid className='px-5'>
      <Row className='justify-content-center m-3'>
        <h1 className='text-center'>
          <span className='text-primary'>tilit</span>
        </h1>
      </Row>
      <LoginForm />
    </Container>
  );
};

export default Login;
