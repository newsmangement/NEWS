using Aliyun.Acs.Core;
using Aliyun.Acs.Core.Exceptions;
using Aliyun.Acs.Core.Http;
using Aliyun.Acs.Core.Profile;
using Confluent.Kafka;
using Confluent.Kafka.Serialization;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MessageCenter
{
    public partial class Form1 : Form
    {
        static Data data;
        static TextBox textBox = new TextBox();
        static bool Flags=true;
        //RichTextBox jkinfo =this.richTextBox1;
        public Form1()
        {
            InitializeComponent();
        }

        private void Button2_Click(object sender, EventArgs e)
        {
            this.textBox3.Text = "监听中...";
            string ip=this.textBox1.Text;
            string topic = this.textBox2.Text;
        
            data = new Data(ip,topic);
            
            Thread t1 = new Thread(consumer);
            t1.Start();
            
            

        }
        public  static void consumer()
        {

            var config = new Dictionary<string, object>
                {
                    { "group.id", "1" },
                    { "bootstrap.servers",data.ip }
                   
                };

            using (var consumer = new Consumer<Null, string>(config, null, new StringDeserializer(Encoding.UTF8)))
            {
                consumer.Subscribe(data.topic);
               
               
                while (Flags)
                {

                    if (consumer.Consume(out Message<Null, string> msg, TimeSpan.FromSeconds(1)))
                    {

                        //MessageBox.Show(msg.Value);
                        string[] res=msg.Value.Split(new char[] { ',' });
                        SendMessage(res[0],res[1]);
                        MessageBox.Show("发给手机号为" + res[0] + "的验证码，已经成功发送");

                        //data.jkinfo="发给手机号为"+msg.Value+"的验证码，已经成功发送";


                    }
                   
                }
            }
        }
        public static bool SendMessage(string phoneNum,string code) {
            IClientProfile profile = DefaultProfile.GetProfile("cn-hangzhou", "LTAI8VrzTlV4qVdi", "ws2oOs5Ivveu0UFfpa8PUwfo78AUzj");
            DefaultAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.Method = MethodType.POST;
            request.Domain = "dysmsapi.aliyuncs.com";
            request.Version = "2017-05-25";
            request.Action = "SendSms";
            // request.Protocol = ProtocolType.HTTP;
            request.AddQueryParameters("PhoneNumbers", phoneNum);
            request.AddQueryParameters("SignName", "浪舟子");
            request.AddQueryParameters("TemplateCode", "SMS_168250218");
            string codeStr = "{\"code\":\""+code+"\"}";
           
            request.AddQueryParameters("TemplateParam", codeStr);
            try
            {
                CommonResponse response = client.GetCommonResponse(request);
                
            }
            catch (ServerException e)
            {
                MessageBox.Show(e.ToString());
            }
            catch (ClientException e)
            {
                MessageBox.Show(e.ToString());
            }
            return true;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void Button1_Click(object sender, EventArgs e)
        {
            this.textBox3.Text = "停止监听";
            Flags = false;
        }
    }
    public class Data {
        public Data(string ip,string topic) {

            this.ip = ip;
            this.topic = topic;
            
            
        }
        public string ip { set; get; }
        public string topic { set; get; }
       
    }
}
