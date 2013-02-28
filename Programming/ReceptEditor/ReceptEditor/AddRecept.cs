using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ReceptEditor.Model;

namespace ReceptEditor
{
    public partial class AddRecept : Form
    {
        private AutoCompleteStringCollection source = new AutoCompleteStringCollection();
        private MainForm mainForm;
        private List<Product> newReceptProducts = new List<Product>();

        public AddRecept()
        {
            InitializeComponent();

            RecomplitSource();
        }

        public AddRecept(MainForm mForm)
        {
            InitializeComponent();

            mainForm = mForm;
            RecomplitSource();
        }

        private void RecomplitSource()
        {
            source.Clear();

            foreach(var prodName in mainForm.ProductList)
            {
                source.Add(prodName.NameProduct);
            }
            
            textBox4.AutoCompleteCustomSource = source;
            textBox4.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            textBox4.AutoCompleteSource = AutoCompleteSource.CustomSource;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(textBox1.Text.Length>0 && textBox3.Text.Length>0 && listBox1.Items.Count>0)
            {
                
            }
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if(textBox4.Text.Length>0 && textBox5.Text.Length>0 && textBox6.Text.Length>0)
            {
                checkForNewProduct();

                String ob;
                if (checkBox1.Checked)
                {
                    ob = "1";
                }
                else
                {
                    ob = "0";
                }
                listBox1.Items.Add(newReceptProducts.Last().IdProduct + "|" + newReceptProducts.Last().NameProduct + "|" +
                                   textBox5.Text + "|" + textBox6.Text+"|"+ob);
                
                textBox4.Text = "";
                textBox5.Text = "";
                textBox6.Text = "";
            }
        }

        private void checkForNewProduct()
        {
            bool isNew = true;
            foreach (var product in mainForm.ProductList)
            {
                if (product.NameProduct.Equals(textBox4))
                {
                    isNew = false;
                    newReceptProducts.Add(product);
                }
            }
            if(isNew)
            {
                Product newProduct = new Product();
                newProduct.IdProduct = Guid.NewGuid().ToString();
                newProduct.NameProduct = textBox4.Text;
                mainForm.ProductList.Add(newProduct);
                newReceptProducts.Add(newProduct);
                RecomplitSource();
            }

        }
    }
}
