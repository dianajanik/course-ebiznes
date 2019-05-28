import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateTransactionState = {
    idUser: string,
    transactionDate: string
    
};

class CreateTransaction extends Component<any, CreateTransactionState>{
  state: CreateTransactionState = {
    idUser: '',
    transactionDate: ''
  };
  componentDidMount(){
    console.log("here")
  }
  render() {
    const {
      idUser,
      transactionDate
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={idUser} className="form-control" id="categoryInput" placeholder="id usr"
             onChange={(e) => { this.setState({ idUser: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={transactionDate} className="form-control" id="nameInput" placeholder="transaction Date" 
            onChange={(e) => { this.setState({ transactionDate: e.target.value})}} />
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
        this.state.idUser !== null
        && this.state.transactionDate !== null
      ) {
        const data = {  
          idUser: parseInt(this.state.idUser),
          transactionDate: parseInt(this.state.transactionDate),
        };
        
        console.log(data);
        
        await axios.post(
          '/transactions',
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
       
export default CreateTransaction;
