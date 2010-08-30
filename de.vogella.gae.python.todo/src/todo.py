
from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.ext import db
from google.appengine.ext.webapp import template
from google.appengine.api import mail

# Todo defines the data model for the Todos
# as it extends db.model the content of the class will automatically stored
class TodoModel(db.Model):
  author           = db.UserProperty(required=True)
  shortDescription = db.StringProperty(required=True)
  longDescription  = db.StringProperty(multiline=True)
  url 	 	       = db.StringProperty()
  created          = db.DateTimeProperty(auto_now_add=True)
  updated 	       = db.DateTimeProperty(auto_now=True)
  dueDate          = db.StringProperty(required=True)
  finished         = db.BooleanProperty()


# The main page where the user can login and logout
# MainPage is a subclass of webapp.RequestHandler and overwrites the get method
class MainPage(webapp.RequestHandler):
    def get(self):
        user = users.get_current_user()
        url = users.create_login_url(self.request.uri)
        url_linktext = 'Login'
                    
        if user:
            url = users.create_logout_url(self.request.uri)
            url_linktext = 'Logout'
# GQL is similar to SQL             
        todos = TodoModel.gql("WHERE author = :author and finished=false",
               author=users.get_current_user())
        
        values = {
            'todos': todos,
	    'numbertodos' : todos.count(),
            'user': user,
            'url': url,
            'url_linktext': url_linktext,
        }
        self.response.out.write(template.render('index.html', values))



# This class creates a new Todo item
class New(webapp.RequestHandler):
    def post(self):
        user = users.get_current_user()
        if user:
    	    testurl = self.request.get('url')
    	    if not testurl.startswith("http://") and testurl:
    		    testurl = "http://"+ testurl
            todo = TodoModel(
                    author  = users.get_current_user(),
                    shortDescription = self.request.get('shortDescription'),
                    longDescription = self.request.get('longDescription'),
                    dueDate = self.request.get('dueDate'),
                    url = testurl,
                    finished = False)
            todo.put();
            self.redirect('/')           


# This class deletes the selected Todo
class Done(webapp.RequestHandler):
    def get(self):
        user = users.get_current_user()
        if user:
            raw_id = self.request.get('id')
            id = int(raw_id)
            todo = TodoModel.get_by_id(id)
            todo.delete()
            self.redirect('/')


#This class emails the task to yourself
class Email(webapp.RequestHandler):
    def get(self):
 	user = users.get_current_user()
	if user:
            raw_id = self.request.get('id')
            id = int(raw_id)
            todo = TodoModel.get_by_id(id)
	    message = mail.EmailMessage(sender=user.email(),
                            subject=todo.shortDescription)
	    message.to = user.email()
	    message.body = todo.longDescription
	    message.send()
   	    self.redirect('/')

# Register the URL with the responsible classes
application = webapp.WSGIApplication(
                                     [('/', MainPage),
                                      ('/new', New),
				      ('/done', Done),
				      ('/email', Email)],
                                     debug=True)

# Register the wsgi application to run
def main():
  run_wsgi_app(application)

if __name__ == "__main__":
  main()