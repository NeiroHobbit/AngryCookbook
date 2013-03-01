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
        private List<Recept> receptList = new List<Recept>();
        private List<Product> productList = new List<Product>();
        private List<PR> PRList = new List<PR>();

        public MainForm()
        {
            InitializeComponent();

            Init();
        }

        public void Init()
        {
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
            ReceptList.Clear();
            ProductList.Clear();
            PRList.Clear();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listBox1.SelectedIndex>-1)
            {
                setReceptName(listBox1.SelectedItem.ToString());
                ReceptEngine engine = new ReceptEngine(this, listBox1.SelectedItem.ToString());
            }
            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if(listBox1.SelectedIndex>-1)
            {
                String idRecept = "";
                foreach (var recept in receptList)
                {
                    if (recept.NameRecept.Equals(listBox1.SelectedItem.ToString()))
                    {
                        receptList.Remove(recept);
                        listBox1.Items.Remove(listBox1.SelectedItem);
                        textBox1.Text = "";
                        textBox2.Text = "";
                        textBox3.Text = "";

                        using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"Recept"))
                        {
                            foreach (var rcpt in receptList)
                            {
                                file.WriteLine(rcpt.IdRecept + "|" + rcpt.NameRecept + "|" + rcpt.TextRecept);
                            }
                            file.Close();
                        }

                        break;
                    }
                }
            }
        }


    }
}
