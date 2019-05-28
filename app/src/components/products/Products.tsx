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
type ProductsState = {
  products: Product[];
};

class Products extends Component<any, ProductsState>{
  state: ProductsState = {
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
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Category</th>
              <th scope="col">Name</th>
              <th scope="col">Price</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => {
              return (
                <tr key={product.idProduct}>
                  <td>{product.productCategory}</td>
                  <td>{product.productName}</td>
                  <td>{product.productPrice}</td>
                  <td>{product.productDescription}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>

    )
  }
}

export default Products;
