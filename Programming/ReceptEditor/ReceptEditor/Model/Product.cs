using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReceptEditor.Model
{
    public class Product
    {
        private String idProduct = "";
        private String _nameProduct = "";

        public string IdProduct
        {
            get { return idProduct; }
            set { idProduct = value; }
        }

        public string NameProduct
        {
            get { return _nameProduct; }
            set { _nameProduct = value; }
        }
    }
}
