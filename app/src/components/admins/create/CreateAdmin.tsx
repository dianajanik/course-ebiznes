import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateAdminState = {
  adminUsername: string,
  adminPassword: string
};

class CreateAdmin extends Component<any, CreateAdminState>{
  state: CreateAdminState = {
    adminUsername: '',
    adminPassword: ''
  };

  render() {
    const {
      adminUsername,
      adminPassword
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={adminUsername} className="form-control" id="categoryInput" placeholder="User name"
             onChange={(e) => { this.setState({ adminUsername: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={adminPassword} className="form-control" id="nameInput" placeholder="Password" 
            onChange={(e) => { this.setState({ adminPassword: e.target.value})}} />
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
        this.state.adminPassword !== null
        && this.state.adminUsername !== null
      ) {
        const data = {
          adminUsername: this.state.adminUsername,
          adminPassword: this.state.adminPassword
        };
        
        console.log(data);
        
        await axios.post(
          '/admins',
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
       
export default CreateAdmin;
