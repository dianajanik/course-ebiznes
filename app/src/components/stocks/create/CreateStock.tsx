import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateStockState = {
    idProduct: string,
    quantity: string
};

class CreateStock extends Component<any, CreateStockState>{
  state: CreateStockState = {
    idProduct: '',
    quantity: ''
  };
  componentDidMount(){
    console.log("here")
  }
  render() {
    const {
      idProduct,
      quantity
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={idProduct} className="form-control" id="categoryInput" placeholder="id productu"
             onChange={(e) => { this.setState({ idProduct: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={quantity} className="form-control" id="nameInput" placeholder="quantity" 
            onChange={(e) => { this.setState({ quantity: e.target.value})}} />
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
      ) {
        const data = {
          idProduct: parseInt(this.state.idProduct),
          quantity: parseInt(this.state.quantity),
        };
        
        console.log(data);
        
        await axios.post(
          '/stocks',
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
       
export default CreateStock;
