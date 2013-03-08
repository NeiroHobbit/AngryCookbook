using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReceptEditor.Model
{
    public class Recept
    {
        private String idRecept = "";
        private String nameRecept = "";
        private String textRecept = "";
        private String typeRecept = "";

        public Recept()
        {
            
        }

        public string IdRecept
        {
            get { return idRecept; }
            set { idRecept = value; }
        }

        public string NameRecept
        {
            get { return nameRecept; }
            set { nameRecept = value; }
        }

        public string TextRecept
        {
            get { return textRecept; }
            set { textRecept = value; }
        }

        public string TypeRecept
        {
            get { return typeRecept; }
            set { typeRecept = value; }
        }
    }
}
