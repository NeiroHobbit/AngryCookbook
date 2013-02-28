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
    public partial class MainForm : Form
    {
        private List<Recept> receptList;
        private List<Product> productList;
        private List<PR> PRList;

        public MainForm()
        {
            InitializeComponent();

            clearAll();
            readRecepts();
            readProducts();
            readPR();
        }

        

        private void readPR()
        {
            PrList = new List<PR>();
            String line;
            var file = new System.IO.StreamReader("PR", Encoding.UTF8);
            while ((line = file.ReadLine()) != null)
            {
                PR rNew = new PR();
                String[] parsRec = line.Split('|');
                rNew.IdP = parsRec[0];
                rNew.IdR = parsRec[1];
                rNew.Kolvo = parsRec[2];
                rNew.Ei = parsRec[3];
                rNew.Obyaz = parsRec[4];
                PrList.Add(rNew);
            }
            file.Close();
            
        }

        private void readProducts()
        {
            ProductList = new List<Product>();
            String line;
            var file = new System.IO.StreamReader("Product", Encoding.UTF8);
            while ((line = file.ReadLine()) != null)
            {
                Product rNew = new Product();
                String[] parsRec = line.Split('|');
                rNew.IdProduct = parsRec[0];
                rNew.NameProduct = parsRec[1];
                ProductList.Add(rNew);
            }
            file.Close();
        }

        private void readRecepts()
        {
            ReceptList = new List<Recept>();
            String line;
            var file = new System.IO.StreamReader("Recept", Encoding.UTF8);
            while ((line = file.ReadLine()) != null)
            {
                Recept rNew = new Recept();
                String[] parsRec = line.Split('|');
                rNew.IdRecept = parsRec[0];
                rNew.NameRecept = parsRec[1];
                rNew.TextRecept = parsRec[2];
                listBox1.Items.Add(parsRec[1]);
                ReceptList.Add(rNew);
            }
            file.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AddRecept addRecept = new AddRecept(this);
            addRecept.ShowDialog();
        }

        public List<Recept> ReceptList
        {
            get { return receptList; }
            set { receptList = value; }
        }

        public List<Product> ProductList
        {
            get { return productList; }
            set { productList = value; }
        }

        public List<PR> PrList
        {
            get { return PRList; }
            set { PRList = value; }
        }

        public void setReceptName(String text)
        {
            textBox1.Text = text;
        }

        public void setReceptText(String text)
        {
            textBox3.Text = text;
        }

        public void setReceptIng(String text)
        {
            textBox2.Text = text;
        }


        private void clearAll()
        {
            listBox1.Items.Clear();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            setReceptName(listBox1.SelectedItem.ToString());
            ReceptEngine engine = new ReceptEngine(this, listBox1.SelectedItem.ToString());
        }


    }
}
