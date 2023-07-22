import CartItem from './CartItem';
import { Button, Container, Row, Col } from 'react-bootstrap';

const Cart = ({ courses, onClickRegister }) => {
  const totalPrice = courses.reduce((pre, cur) => pre + cur.price, 0);

  const handleRegister = () => {
    onClickRegister(courses.map((course) => course.id));
  };

  return (
    <Container>
      <div className='mb-3'>
        <h5>
          <b>수강바구니</b>
        </h5>
      </div>
      <hr />
      {courses.map((course) => (
        <CartItem {...course} />
      ))}
      <hr />
      <Row className='pt-2 pb-3'>
        <Col>
          <h5>총 금액</h5>
        </Col>
        <Col className='text-end'>
          <h5>{totalPrice.toLocaleString('ko-KR')}원</h5>
        </Col>
      </Row>
      <div className='d-grid gap-2'>
        <Button disabled={courses.length === 0} onClick={handleRegister}>
          수강신청하기
        </Button>
      </div>
    </Container>
  );
};

export default Cart;
