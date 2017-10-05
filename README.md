# EventMing
Send easily events in Android

HOW TO USE

# You have to subscribe Activity or Fragment who receive events in onResume()

You can subscribe many events...

```javascript
@Override
protected void onResume() {
  super.onResume();
  EventMing.subscribeNotifications(getApplicationContext(), "myIdentifier");
  EventMing.subscribeNotifications(getApplicationContext(), "myOtherIdentifier");
  
}
```

Send notification

```javascript
Bundle bundle = new Bundle();
bundle.putString("input", mInputET.getText().toString());
EventMing.sendNotification(getApplicationContext(), "myIdentifier", bundle);
```

Receive events

```javascript
EventMing.setOnNotificationReceived(new EventMing.OnNotificationReceived() {
            @Override
            public void onReceived(String identifier, @Nullable Bundle data) {
                if (data != null) {
                    if(identifier.equals("myIdentifier")){
                        mResultTV.setText("First Identifier: " + identifier + " / Data: " + data.getString("input"));
                    }
                }
            }
});
```
