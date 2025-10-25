import { Component, OnInit } from "@angular/core";
import { ProductModule } from "../../module/product/product.module";
import { ProductService } from "../../service/product.service";
import { ActivatedRoute, Router } from "@angular/router";

import { faBoxes, faDollarSign, faImage, faPlusCircle, faSave, faTag }
  from '@fortawesome/free-solid-svg-icons';



@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrl: './updateproduct.component.css'
})



export class UpdateproductComponent implements OnInit {


  imageUrl: string = "http://localhost:8085/images/medicines/product/";

  id: number = 0;
  product: ProductModule = new ProductModule();
  imageFile !: File;
 

  faPlusCircle = faPlusCircle;
  faTag = faTag;
  faImage = faImage;
  faDollarSign = faDollarSign;
  faBoxes = faBoxes;
  faSave = faSave;



  // Helper strings for <input type="date">
  manufactureDateString: string = '';

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.params['id'];

    console.log("ID:  " + this.id);

    this.productService.getById(this.id).subscribe({
      next: res => {
        this.product = res;

        // Convert manufactureDate (Date) to string yyyy-MM-dd
        if (this.product.manufactureDate) {
          this.manufactureDateString = this.formatDate(this.product.manufactureDate); // OK
        }

        // expiryDate is already string, just make sure it’s yyyy-MM-dd
        if (this.product.expiryDate) {
          this.product['expiryDate'] = this.product.expiryDate.split('T')[0];
        }

        console.log(this.product);
      },
      error: error => {
        console.error('Error fetching product:', error);
      }
    });
  }

  onFileChange(event: any) {
  const file = event.target.files[0];
  if (file) {
    this.imageFile = file; // new file selected
  }
  // If no file selected, imageFile remains null → old image stays
}


  formatDate(date: Date): string {
    const d = new Date(date);
    const month = ('0' + (d.getMonth() + 1)).slice(-2);
    const day = ('0' + d.getDate()).slice(-2);
    const year = d.getFullYear();
    return `${year}-${month}-${day}`;
  }




  // Example after fetching product from API


  updateProduct() {
    
    // Convert helper string to Date for backend
    if (this.manufactureDateString) {
      this.product.manufactureDate = new Date(this.manufactureDateString);
    }




    
    console.log('Product to update:', this.product); // Debug line to check product data

    this.productService.updateProduct(this.id, this.product, this.imageFile).subscribe({
      next: res => {
        console.log('Product updated successfully:', res);
        this.router.navigate(['/viewproduct']);
      },
      error: error => {
        console.error('Error updating product:', error);
      }
    });

    
  }
}