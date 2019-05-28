import React, { Component, FormEvent } from 'react';
import axios from 'axios'
import { interfaceDeclaration } from '@babel/types';

type CreateProductState = {
  productCategory: string,
  productPrice: string,
  productName: string,
  productDescription: string,
  productPhoto: string,
  productNotSaled: string
};

class CreateProduct extends Component<any, CreateProductState>{
  state: CreateProductState = {
    productCategory: '',
    productPrice: '',
    productName: '',
    productDescription: '',
    productPhoto: '',
    productNotSaled: ''
  };

  render() {
    const {
      productCategory,
      productPrice,
      productName,
      productDescription,
      productPhoto,
      productNotSaled
    } = this.state;

    return (
      <div className="container">
      <form onSubmit={this.submitFunction.bind(this)}>
          <div className="form-group">
            <input type="text" value={productCategory} className="form-control" id="categoryInput" placeholder="Category"
             onChange={(e) => { this.setState({ productCategory: e.target.value})}}/>
          </div>
          <div className="form-group">
            <input type="text" value={productName} className="form-control" id="nameInput" placeholder="Name" 
            onChange={(e) => { this.setState({ productName: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={productPrice} className="form-control" id="priceInput" placeholder="Price"
             onChange={(e) => { this.setState({ productPrice: e.target.value})}} />
          </div>
          <div className="form-group">
            <input type="text" value={productDescription} className="form-control" id="priceInput" placeholder="Description"
             onChange={(e) => { this.setState({ productDescription: e.target.value})}} />
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
        this.state.productCategory !== null
        && this.state.productName !== null
        && this.state.productPrice !== null
        && this.state.productDescription !== null
      ) {
        const data = {
          productCategory: parseInt(this.state.productCategory),
          productPrice: parseInt(this.state.productPrice),
          productName: this.state.productName,
          productDescription: this.state.productDescription,
          productPhoto: "No photo",
          productNotSaled: "false"
        };
        
        console.log(data);
        
        await axios.post(
          '/products',
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
       
export default CreateProduct;
