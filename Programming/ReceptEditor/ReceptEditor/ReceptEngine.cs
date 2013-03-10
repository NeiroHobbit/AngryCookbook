using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ReceptEditor.Model;

namespace ReceptEditor
{
    class ReceptEngine
    {
        private MainForm mainForm;

        public ReceptEngine(MainForm mForm, String receptName)
        {
            mainForm = mForm;
            Recept findRecept = new Recept();

            foreach (var recept in mainForm.ReceptList)
            {
                if(recept.NameRecept.Equals(receptName))
                {
                    findRecept = recept;
                    break;
                }
            }

            String rec = findRecept.TextRecept.Replace("newline", "\r\n");
            mainForm.setReceptText(rec);

            //TODO: необязательные ингредиенты в конце списка
            String ingredients = "";
            foreach (var rp in mainForm.PrList)
            {
                if(rp.IdR.Equals(findRecept.IdRecept))
                {
                    if (ingredients.Length>0)
                    {
                        ingredients += ", ";
                    }
                    ingredients += (getIngr(rp.IdP, rp.Kolvo + " " + rp.Ei)).ToLower();

                }
            }
            mainForm.setReceptIng(ingredients);

            if (findRecept.TypeRecept.Equals(""))
            {
                mainForm.setReceptType("");
            }
            else
            {
                mainForm.setReceptType(mainForm.RtDict.getValueByKey(findRecept.TypeRecept));
            }

        }

        private String getIngr(String idProd, String appendix)
        {
            String result = "";
            foreach (var product in mainForm.ProductList)
            {
                if (product.IdProduct.Equals(idProd))
                {
                    result = product.NameProduct + " " + appendix;
                    break;
                }
            }
            return result;
        }
    }
}
