﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReceptEditor.Model
{
    
    public class RTDict
    {
        private SortedDictionary<String, String> typeDictionary;

        public RTDict()
        {
            typeDictionary = new SortedDictionary<String, String>();

            String line;
            var file = new System.IO.StreamReader("rec_txt", Encoding.UTF8);
            while ((line = file.ReadLine()) != null)
            {
                try
                {
                    String[] parsT = line.Split(' ');
                    String key = parsT[0];
                    String value = "";
                    for (int i = 1; i < parsT.Length; i++)
                    {
                        value += parsT[i];
                        if (i != parsT.Length-1)
                        {
                            value += " ";
                        }
                    }
                    addType(key, value);
                }
                catch (Exception e)
                {
                    throw;
                }
 
            }
            file.Close();
        }

        private void addType(String k, String v)
        {
            typeDictionary.Add(k, v);  
        }

        public List<String[]> getStringArray()
        {
            List<String[]> list = new List<string[]>();

            foreach (KeyValuePair<string, string> keyValuePair in typeDictionary)
            {
                String[] s = new string[2];
                s[0] = keyValuePair.Key;
                s[1] = keyValuePair.Value;
                list.Add(s);
            }
                
            return list;
        }

        public String getKeyByValue(String value)
        {
            String key = "";
            foreach (KeyValuePair<string, string> keyValuePair in typeDictionary)
            {
                if (keyValuePair.Value.Equals(value))
                {
                    key = keyValuePair.Key;
                    break;
                }
            }

            return key;
        }

        public String getValueByKey(String key)
        {
            String value = "";
            value = typeDictionary[key];

            return value;
        }
    }
}
