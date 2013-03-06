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
                if(newReceptProducts.Count!=0)
                {
                    Recept recept = new Recept();
                    recept.IdRecept = Guid.NewGuid().ToString();
                    recept.NameRecept = textBox1.Text;
                    recept.TextRecept = textBox3.Text.Replace("\r\n", "newline");
                    mainForm.ReceptList.Add(recept);

                    foreach (var listItem in listBox1.Items)
                    {
                        PR pr = new PR();
                        pr.IdP = listItem.ToString().Split('|')[0];
                        pr.IdR = recept.IdRecept;
                        pr.Kolvo = listItem.ToString().Split('|')[2];
                        pr.Ei = listItem.ToString().Split('|')[3];
                        pr.Obyaz = listItem.ToString().Split('|')[4];
                        mainForm.PrList.Add(pr);
                    }

                    using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"Recept"))
                    {
                        foreach (var rcpt in mainForm.ReceptList)
                        {
                            file.WriteLine(rcpt.IdRecept + "|" + rcpt.NameRecept + "|" + rcpt.TextRecept);
                        }
                        file.Close();
                    }

                    using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"PR"))
                    {
                        foreach (var pr in mainForm.PrList)
                        {
                            file.WriteLine(pr.IdP + "|" + pr.IdR + "|" + pr.Kolvo + "|" + pr.Ei + "|" + pr.Obyaz);
                        }
                        file.Close();
                    }
                    mainForm.Init();
                    Close();
                }
                
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
                if (product.NameProduct.ToString().Equals(textBox4.Text))
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

                rewriteProductsFile();
            }

        }

        private void rewriteProductsFile()
        {
            using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"Product"))
            {
                foreach (var product in mainForm.ProductList)
                {
                    file.WriteLine(product.IdProduct + "|" + product.NameProduct);
                }
                file.Close();
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (listBox1.SelectedIndex>-1)
            {
                foreach (var product in mainForm.ProductList)
                {
                    if (product.NameProduct.Equals(listBox1.SelectedItem.ToString().Split('|')[1]))
                    {
                        mainForm.ProductList.Remove(product);
                        listBox1.Items.Remove(listBox1.SelectedItem);
                        rewriteProductsFile();
                        break;
                    }
                }
            }
        }
    }
}
