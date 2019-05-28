import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateSubransactionState = {
    idProduct: string,
    subtransactionQuantity: string
};

class CreateSubtransaction extends Component<any, CreateSubransactionState>{
  state: CreateSubransactionState = {
    idProduct: '',
    subtransactionQuantity: ''
  };
  componentDidMount(){
    console.log("here")
  }
  render() {
    const {
      idProduct,
      subtransactionQuantity
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={idProduct} className="form-control" id="categoryInput" placeholder="id product"
             onChange={(e) => { this.setState({ idProduct: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={subtransactionQuantity} className="form-control" id="nameInput" placeholder="quantity" 
            onChange={(e) => { this.setState({ subtransactionQuantity: e.target.value})}} />
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
        this.state.idProduct !== null
        && this.state.subtransactionQuantity !== null
      ) {
        const data = {  
            idProduct: parseInt(this.state.idProduct),
            subtransactionQuantity: parseInt(this.state.subtransactionQuantity),
        };
        
        console.log(data);
        
        await axios.post(
          '/subtransactions',
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
       
export default CreateSubtransaction;
