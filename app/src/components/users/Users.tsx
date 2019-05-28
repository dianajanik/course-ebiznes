import React, { Component } from 'react';
import logo from './logo.svg';
import axios from 'axios'
type User = {
  idUser: number,
  userEmail: string,
  userPassword: string,
  userName: string,
  userSurname: string,
  userStreet: string,
  userHomeNumber: string,
  userCity: string,
  userCountry: string,
  userPostalCode: string
}
type UsersState = {
  users: User[];
};

class Users extends Component<any, UsersState>{
  state: UsersState = {
    users: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/users')
      const users: User[] = resp.data;
      this.setState({ users: users })
      console.log(users)
    } catch (e) {
      console.log(e)
    }
  }
  render() {
    const { users } = this.state;
    return (
      <div className="container">
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Email</th>
              <th scope="col">Password</th>
              <th scope="col">Name</th>
              <th scope="col">Surname</th>
              <th scope="col">Street</th>
              <th scope="col">Home number</th>
              <th scope="col">City</th>
              <th scope="col">Country</th>
              <th scope="col">PostalCode</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => {
              return (
                <tr key={user.idUser}>
                  <td>{user.idUser}</td>
                  <td>{user.userEmail}</td>
                  <td>{user.userPassword}</td>
                  <td>{user.userName}</td>
                  <td>{user.userSurname}</td>
                  <td>{user.userStreet}</td>
                  <td>{user.userHomeNumber}</td>
                  <td>{user.userCity}</td>
                  <td>{user.userCountry}</td>
                  <td>{user.userPostalCode}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    )
  }
}

export default Users;
