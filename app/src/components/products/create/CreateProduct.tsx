import React, { Component } from 'react';
import logo from './logo.svg';
import axios from 'axios'
type Product = {
  idProduct: number,
  productCategory: String,
  productPrice: number,
  productName: String,
  productDescription: String,
  productPhoto: String,
  productNotSaled: boolean
}
type CreateProductState = {
  products: Product[];
};

class CreateProduct extends Component<any, CreateProductState>{
  state: CreateProductState = {
    products: [],
  };

  async componentDidMount() {
    try {
      const resp = await axios.get('/products')
      const products: Product[] = resp.data;
      this.setState({ products })
      console.log(products)
    } catch (e) {
      console.log(e)
    }
  }
  render() {
    const { products } = this.state;
    return (
      <div className="container">
        <form>
          <div className="form-group">
            <input type="email" className="form-control" id="NameInput" placeholder="Name"/>
            <button type="submit" className="btn btn-primary">Submit</button>
          </div>
        </form>
      </div>

      )
          }
        }
        
  export default CreateProduct;
