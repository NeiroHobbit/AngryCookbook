using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ReceptEditor
{
    public partial class AddRecept : Form
    {
        private AutoCompleteStringCollection source = new AutoCompleteStringCollection();
        public AddRecept()
        {
            InitializeComponent();

            RecomplitSource();
        }

        private void RecomplitSource()
        {
            source.AddRange(new string[]
                                {
                                    "January",
                                    "February",
                                    "March",
                                    "April",
                                    "May",
                                    "June",
                                    "July",
                                    "August",
                                    "September",
                                    "October",
                                    "November",
                                    "December"
                                });


            textBox4.AutoCompleteCustomSource = source;
            textBox4.AutoCompleteMode = AutoCompleteMode.SuggestAppend;
            textBox4.AutoCompleteSource = AutoCompleteSource.CustomSource;
        }
    }
}
