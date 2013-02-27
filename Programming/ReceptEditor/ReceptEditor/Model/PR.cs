using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReceptEditor.Model
{
    public class PR
    {
        private String idP = "";
        private String idR = "";
        private String kolvo = "";
        private String ei = "";
        private String obyaz = "";

        public string IdP
        {
            get { return idP; }
            set { idP = value; }
        }

        public string IdR
        {
            get { return idR; }
            set { idR = value; }
        }

        public string Kolvo
        {
            get { return kolvo; }
            set { kolvo = value; }
        }

        public string Ei
        {
            get { return ei; }
            set { ei = value; }
        }

        public string Obyaz
        {
            get { return obyaz; }
            set { obyaz = value; }
        }
    }
}
