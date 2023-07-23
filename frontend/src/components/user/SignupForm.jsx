import { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const SignupForm = ({ onClickSignup }) => {
  const [validated, setValidated] = useState(false);
  const [form, setForm] = useState({ email: '', password: '' });
  const [confirmPassword, setConfirmPassword] = useState('');
  const { email, password } = form;

  const handleSubmit = (e) => {
    e.preventDefault();

    if (e.currentTarget.checkValidity() === false) {
      e.stopPropagation();
    } else {
      onClickSignup(form);
    }
    setValidated(true);
  };

  const handleEmailChange = (e) => {
    setForm({ ...form, email: e.target.value });
    const isValid = e.target.checkValidity();
    setValidated(isValid);
  };

  const handlePasswordChange = (e) => {
    setForm({ ...form, password: e.target.value });
    const length = e.target.length;
    setValidated(length >= 8 && length <= 32);
  };

  const handleConfirmPasswordChange = (e) => {
    setConfirmPassword(e.target.value);
    setValidated(password === e.target.value);
  };

  return (
    <Container style={{ maxWidth: '560px' }}>
      <h4 className='mb-3 mt-5'>회원가입</h4>
      <Form noValidate validated={validated} onSubmit={handleSubmit}>
        <Form.Group controlId='email' className='mb-3'>
          <Form.Label>이메일</Form.Label>
          <Form.Control
            type='email'
            placeholder='name@example.com'
            required
            value={email}
            onChange={handleEmailChange}
            isInvalid={email.length === 0}
          />
          <Form.Control.Feedback type='invalid'>이메일 형식이 올바르지 않습니다.</Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId='password' className='mb-3'>
          <Form.Label>비밀번호</Form.Label>
          <Form.Control
            type='password'
            placeholder='password'
            required
            value={password}
            onChange={handlePasswordChange}
            isInvalid={password.length < 8 || password.length > 32}
          />
          <Form.Control.Feedback type='invalid'>비밀번호는 8자 이상, 32자 이하여야 합니다.</Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId='confirmPassword' className='mb-3'>
          <Form.Label>비밀번호 확인</Form.Label>
          <Form.Control
            type='password'
            placeholder='password'
            required
            value={confirmPassword}
            onChange={handleConfirmPasswordChange}
            isInvalid={password !== confirmPassword}
          />
          <Form.Control.Feedback type='invalid'>비밀번호가 일치하지 않습니다.</Form.Control.Feedback>
        </Form.Group>

        <hr className='my-4' />

        <Row className='mt-3'>
          <Col>
            <div className='d-grid'>
              <Button variant='primary' size='lg' type='submit' disabled={!validated}>
                회원가입
              </Button>
            </div>
          </Col>
          <Col>
            <Link to={`/`} style={{ textDecoration: 'none', color: 'inherit' }}>
              <div className='d-grid'>
                <Button variant='secondary' size='lg'>
                  취소
                </Button>
              </div>
            </Link>
          </Col>
        </Row>
      </Form>
    </Container>
  );
};

export default SignupForm;
