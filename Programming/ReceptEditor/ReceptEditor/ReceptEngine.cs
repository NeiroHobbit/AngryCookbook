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

            mainForm.setReceptText(findRecept.TextRecept);

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
