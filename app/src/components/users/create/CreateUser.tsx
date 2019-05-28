import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateUserState = {
    userEmail: string,
    userPassword: string,
    userName: string,
    userSurname: string,
    userStreet: string,
    userHomeNumber: string,
    userCity: string,
    userCountry: string,
    userPostalCode: string
};

class CreateUser extends Component<any, CreateUserState>{
  state: CreateUserState = {
    userEmail:'',
    userPassword: '',
    userName: '',
    userSurname: '',
    userStreet:'',
    userHomeNumber: '',
    userCity: '',
    userCountry: '',
    userPostalCode: ''
  };

  render() {
    const {
        userEmail,
        userPassword,
        userName,
        userSurname,
        userStreet,
        userHomeNumber,
        userCity,
        userCountry,
        userPostalCode
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={userEmail} className="form-control" id="emailInput" placeholder="Email"
             onChange={(e) => { this.setState({ userEmail: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={userPassword} className="form-control" id="nameInput" placeholder="Passwors" 
            onChange={(e) => { this.setState({ userPassword: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userName} className="form-control" id="priceInput" placeholder="Name"
             onChange={(e) => { this.setState({ userName: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userSurname} className="form-control" id="priceInput" placeholder="Description"
             onChange={(e) => { this.setState({ userSurname: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userStreet} className="form-control" id="priceInput" placeholder="Street"
             onChange={(e) => { this.setState({ userStreet: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userHomeNumber} className="form-control" id="priceInput" placeholder="Home number"
             onChange={(e) => { this.setState({ userHomeNumber: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userCity} className="form-control" id="priceInput" placeholder="City"
             onChange={(e) => { this.setState({ userCity: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userCountry} className="form-control" id="priceInput" placeholder="Country"
             onChange={(e) => { this.setState({ userCountry: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={userPostalCode} className="form-control" id="priceInput" placeholder="Postal code"
             onChange={(e) => { this.setState({ userPostalCode: e.target.value})}} />
          </div>
          <button type="submit" className="btn btn-primary">Submit</button>
        </form>
      </div>
      )
  }

async submitFunction(e: FormEvent) {
    e.preventDefault();
    try {
      if (
        this.state.userEmail !== null
        && this.state.userPassword !== null
        && this.state.userName !== null
        && this.state.userSurname !== null
      ) {
        const data = {
          userEmail: this.state.userEmail,
          userPassword: this.state.userPassword,
          userName: this.state.userName,
          userSurname: this.state.userSurname,
          userStreet: this.state.userStreet,
          userHomeNumber: this.state.userHomeNumber,
          userCity: this.state.userCity,
          userCountry: this.state.userCountry,
          userPostalCode: this.state.userPostalCode
        };
        
        console.log(data);
        
        await axios.post(
          '/users',
          data,
          {
            headers:{
              'Content-Type': 'application/json'
            }
          }
        );
      }
    } catch (error) {
      console.log(error);
    }
  }


}
       
export default CreateUser;
