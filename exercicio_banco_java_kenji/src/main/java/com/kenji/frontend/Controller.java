import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Controller {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Registro e Login</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"menu\">\n" +
                    "            <a href=\"#\" class=\"tablink active\" onclick=\"openTab(event, 'registerForm')\">Registro</a>\n" +
                    "            <a href=\"#\" class=\"tablink\" onclick=\"openTab(event, 'loginForm')\">Login</a>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-container active\" id=\"registerForm\">\n" +
                    "            <h2>Registro</h2>\n" +
                    "            <input type=\"text\" id=\"username\" placeholder=\"Usuário\">\n" +
                    "            <input type=\"password\" id=\"password\" placeholder=\"Senha\">\n" +
                    "            <input type=\"password\" id=\"confirmPassword\" placeholder=\"Confirmar Senha\">\n" +
                    "            <button onclick=\"register()\">Registrar</button>\n" +
                    "        </div>\n" +
                    "        <div class=\"form-container\" id=\"loginForm\">\n" +
                    "            <h2>Login</h2>\n" +
                    "            <input type=\"text\" id=\"loginUsername\" placeholder=\"Usuário\">\n" +
                    "            <input type=\"password\" id=\"loginPassword\" placeholder=\"Senha\">\n" +
                    "            <button onclick=\"login()\">Login</button>\n" +
                    "        </div>\n" +
                    "        <div id=\"dashboard\" style=\"display:none;\">\n" +
                    "            <h2>Bem-vindo, Admin!</h2>\n" +
                    "            <button onclick=\"logout()\">Logout</button>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <script>\n" +
                    "        function openTab(evt, tabName) {\n" +
                    "            var i, formContainer, tablinks;\n" +
                    "            formContainer = document.getElementsByClassName(\"form-container\");\n" +
                    "            for (i = 0; i < formContainer.length; i++) {\n" +
                    "                formContainer[i].style.display = \"none\";\n" +
                    "            }\n" +
                    "            tablinks = document.getElementsByClassName(\"tablink\");\n" +
                    "            for (i = 0; i < tablinks.length; i++) {\n" +
                    "                tablinks[i].classList.remove(\"active\");\n" +
                    "            }\n" +
                    "            document.getElementById(tabName).style.display = \"block\";\n" +
                    "            evt.currentTarget.classList.add(\"active\");\n" +
                    "        }\n" +
                    "\n" +
                    "        function register() {\n" +
                    "            var username = document.getElementById('username').value;\n" +
                    "            var password = document.getElementById('password').value;\n" +
                    "            var confirmPassword = document.getElementById('confirmPassword').value;\n" +
                    "\n" +
                    "            if (password !== confirmPassword) {\n" +
                    "                alert('As senhas não coincidem!');\n" +
                    "                return;\n" +
                    "            }\n" +
                    "\n" +
                    "            alert('Usuário registrado com sucesso!');\n" +
                    "            document.getElementById('username').value = '';\n" +
                    "            document.getElementById('password').value = '';\n" +
                    "            document.getElementById('confirmPassword').value = '';\n" +
                    "        }\n" +
                    "\n" +
                    "        function login() {\n" +
                    "            var username = document.getElementById('loginUsername').value;\n" +
                    "            var password = document.getElementById('loginPassword').value;\n" +
                    "\n" +
                    "            alert('Usuário logado com sucesso!');\n" +
                    "            document.getElementById('loginUsername').value = '';\n" +
                    "            document.getElementById('loginPassword').value = '';\n" +
                    "            document.getElementById('registerForm').style.display = 'none';\n" +
                    "            document.getElementById('loginForm').style.display = 'none';\n" +
                    "            document.getElementById('dashboard').style.display = 'block';\n" +
                    "        }\n" +
                    "\n" +
                    "        function logout() {\n" +
                    "            alert('Usuário deslogado com sucesso!');\n" +
                    "            document.getElementById('dashboard').style.display = 'none';\n" +
                    "            document.getElementById('registerForm').style.display = 'none';\n" +
                    "            document.getElementById('loginForm').style.display = 'block';\n" +
                    "        }\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>";

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
